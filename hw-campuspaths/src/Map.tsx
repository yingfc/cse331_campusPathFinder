/*
 * Copyright (C) 2021 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Winter Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

import React, {Component} from 'react';
import "./Map.css";

interface MapState {
    backgroundImage: HTMLImageElement | null;
    startImage: HTMLImageElement | null;
    destinationImage: HTMLImageElement | null;
}

interface MapProps {
    path: any;
}

class Map extends Component<MapProps, MapState> {

    // NOTE:
    // This component is a suggestion for you to use, if you would like to.
    // It has some skeleton code that helps set up some of the more difficult parts
    // of getting <canvas> elements to display nicely with large images.
    //
    // If you don't want to use this component, you're free to delete it.

    canvas: React.RefObject<HTMLCanvasElement>;

    constructor(props: MapProps) {
        super(props);
        this.state = {
            backgroundImage: null,
            startImage: null,
            destinationImage: null
        };
        this.canvas = React.createRef();
    }

    componentDidMount() {
        this.fetchAndSaveImage();
        this.drawBackgroundImage();
    }

    componentDidUpdate() {
        this.drawBackgroundImage();
        this.drawPath();
    }

    fetchAndSaveImage() {
        // Creates an Image object, and sets a callback function
        // for when the image is done loading (it might take a while).
        let background: HTMLImageElement = new Image();
        let start: HTMLImageElement = new Image();
        let destination: HTMLImageElement = new Image();
        background.onload = () => {
            this.setState({
                backgroundImage: background
            });
        };
        start.onload = () => {
            this.setState({
                startImage: start
            })
        }
        destination.onload = () => {
            this.setState({
                destinationImage: destination
            })
        }
        // Once our callback is set up, we tell the image what file it should
        // load from. This also triggers the loading process.
        background.src = "./campus_map.jpg";
        start.src = "./start.jpg";
        destination.src = "./destination_flag.jpg";
    }

    drawBackgroundImage() {
        let canvas = this.canvas.current;
        if (canvas === null) throw Error("Unable to draw, no canvas ref.");
        let ctx = canvas.getContext("2d");
        if (ctx === null) throw Error("Unable to draw, no valid graphics context.");
        //
        if (this.state.backgroundImage !== null) { // This means the image has been loaded.
            // Sets the internal "drawing space" of the canvas to have the correct size.
            // This helps the canvas not be blurry.
            ctx.clearRect(0, 0, this.state.backgroundImage.width, this.state.backgroundImage.height);

            canvas.width = this.state.backgroundImage.width;
            canvas.height = this.state.backgroundImage.height;
            ctx.drawImage(this.state.backgroundImage, 0, 0);
        }
    }

    drawPath() {
        let canvas = this.canvas.current;
        if (canvas === null) throw Error("Unable to draw, no canvas ref.");
        let ctx = canvas.getContext("2d");
        if (ctx === null) throw Error("Unable to draw, no valid graphics context.");

        const coordinates = this.getCoordinates();
        ctx.lineWidth = 12;
        ctx.strokeStyle = "magenta";
        ctx.beginPath();
        let destinationPoint: [number, number] = [0, 0];

        if (this.state.startImage !== null && coordinates.length > 0) {
            const img = this.state.startImage;
            ctx.drawImage(img, coordinates[0][0] - 1.4 * img.width, coordinates[0][1] - 1.4 * img.height, img.width * 1.4, img.height * 1.4);
        }
        for (let i = 0; i < coordinates.length - 1; i++) {
            ctx.moveTo(coordinates[i][0], coordinates[i][1]);
            ctx.lineTo(coordinates[i+1][0], coordinates[i+1][1]);
            ctx.stroke();
            if (i === coordinates.length - 2) {
                destinationPoint = [coordinates[i+1][0], coordinates[i+1][1]];
            }
        }
        if (this.state.destinationImage !== null && destinationPoint !== [0, 0]) {
            ctx.drawImage(this.state.destinationImage, destinationPoint[0], destinationPoint[1] - 150, 150, 150);
        }
    }

    getCoordinates = (): [number, number][] => {
        let coordinates: [number, number][] = [];
        if (this.props.path !== "") {
            const currPath = this.props.path;
            coordinates.push([currPath.start.x, currPath.start.y]);
            for (let i = 0; i < currPath.path.length; i++) {
                const onePath = currPath.path[i].end;
                coordinates.push([onePath.x, onePath.y]);
            }
        }
        return coordinates;
    }

    render() {
        return (
            <canvas ref={this.canvas}/>
        )
    }
}

export default Map;