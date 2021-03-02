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

interface GridProps {
    size: number;    // size of the grid to display
    width: number;   // width of the canvas on which to draw
    height: number;  // height of the canvas on which to draw
    edges: string[];
}

interface GridState {
    backgroundImage: any,  // image object rendered into the canvas (once loaded)
}

/**
 *  A simple grid with a variable size
 *
 *  Most of the assignment involves changes to this class
 */
class Grid extends Component<GridProps, GridState> {

    canvasReference: React.RefObject<HTMLCanvasElement>

    constructor(props: GridProps) {
        super(props);
        this.state = {
            backgroundImage: null  // An image object to render into the canvas.
        };
        this.canvasReference = React.createRef();
    }

    componentDidMount() {
        // Since we're saving the image in the state and re-using it any time we
        // redraw the canvas, we only need to load it once, when our component first mounts.
        this.fetchAndSaveImage();
        this.redraw();
    }

    componentDidUpdate() {
        this.redraw()
    }

    fetchAndSaveImage() {
        // Creates an Image object, and sets a callback function
        // for when the image is done loading (it might take a while).
        const background = new Image();
        background.onload = () => {
            this.setState({
                backgroundImage: background
            });
        };
        // Once our callback is set up, we tell the image what file it should
        // load from. This also triggers the loading process.
        background.src = "./image.jpg";
    }

    redraw = () => {
        if (this.canvasReference.current === null) {
            throw new Error("Unable to access canvas.");
        }
        const ctx = this.canvasReference.current.getContext('2d');
        if (ctx === null) {
            throw new Error("Unable to create canvas drawing context.");
        }

        // First, let's clear the existing drawing so we can start fresh:
        ctx.clearRect(0, 0, this.props.width, this.props.height);

        // Once the image is done loading, it'll be saved inside our state, and we can draw it.
        // Otherwise, we can't draw the image, so skip it.
        if (this.state.backgroundImage !== null) {
            ctx.drawImage(this.state.backgroundImage, 0, 0);
        }

        // Draw all the dots.
        const coordinates = this.getCoordinates();
        for (let coordinate of coordinates) {
            this.drawCircle(ctx, coordinate);
        }

        if (this.props.edges.toString() !== "") {
            const edgeEntry: string[] = this.props.edges;
            let lineCount: number = 0;
            for (let e of edgeEntry) {
                if (e.length > 0) {
                    lineCount++;
                    let info = e.split(" ");
                    if (info.length === 3) {
                        let startX: number = parseInt(info[0].split(",")[0]);
                        let startY: number = parseInt(info[0].split(",")[1]);
                        let endX: number = parseInt(info[1].split(",")[0]);
                        let endY: number = parseInt(info[1].split(",")[1]);
                        let widthThreshold: number = Math.max(startX, endX);
                        let lengthThreshold: number = Math.max(startY, endY);
                        let size: number = this.props.size;
                        if (widthThreshold >= size || lengthThreshold >= size) {
                            alert("Cannot draw edges, grid myst be at least size " + Math.max(widthThreshold, lengthThreshold));
                            ctx.clearRect(0, 0, this.props.width, this.props.height);
                            if (this.state.backgroundImage !== null) {
                                ctx.drawImage(this.state.backgroundImage, 0, 0);
                            }
                            for (let coordinate of coordinates) {
                                this.drawCircle(ctx, coordinate);
                            }
                            break;
                        } else {
                            let sourceNode: [number, number] = [this.props.width / (size + 1) * (startX + 1), this.props.width / (size + 1) * (startY + 1)];
                            let destNode: [number, number] = [this.props.width / (size + 1) * (endX + 1), this.props.width / (size + 1) * (endY + 1)];
                            this.drawEdge(ctx, sourceNode, destNode, info[2]);
                        }
                    } else {
                        alert("Line " + lineCount + ": Missing a portion of the line, or missing a space.");
                    }
                }
            }
        }
    };

    /**
     * Returns an array of coordinate pairs that represent all the points where grid dots should
     * be drawn.
     */
    getCoordinates = (): [number, number][] => {
        let grid : [number, number][] = [];
        const width : number = this.props.width;
        const size : number = this.props.size;
        for (let i = 0; i < size; i++) {
            for (let j = 0; j < size; j++) {
                grid.push([(width / (size+1)) * (i + 1), (width / (size+1)) * (j + 1)]);
            }
        }
        return grid;
    };

    drawCircle = (ctx: CanvasRenderingContext2D, coordinate: [number, number]) => {
        ctx.fillStyle = "white";
        // Generally use a radius of 4, but when there are lots of dots on the grid (> 50)
        // we slowly scale the radius down so they'll all fit next to each other.
        const radius = Math.min(4, 100 / this.props.size);
        ctx.beginPath();
        ctx.arc(coordinate[0], coordinate[1], radius, 0, 2 * Math.PI);
        ctx.fill();
    };

    drawEdge = (ctx: CanvasRenderingContext2D, sourceCoordinate: [number, number], destCoordinate: [number, number], color: string) => {
        console.log("drawEdge called");
        ctx.lineWidth = Math.min(4, 200 / this.props.size);
        ctx.strokeStyle = color;
        ctx.beginPath();
        ctx.moveTo(sourceCoordinate[0], sourceCoordinate[1]);
        ctx.lineTo(destCoordinate[0], destCoordinate[1]);
        ctx.stroke();
    }

    render() {
        return (
            <div id="grid">
                <canvas ref={this.canvasReference} width={this.props.width} height={this.props.height}/>
                <p>Current Grid Size: {this.props.size}</p>
            </div>
        );
    }
}

export default Grid;
