import React, {Component} from "react";
import {Path} from "./Interface";

interface PathFinderProps {
    startNode: string;      // the start building to be used for path finding
    endNode: string;        // the end building to be used for path finding
    onChange(newPath: Path): void;  // called when new path is created
}

interface PathFinderState {
    currPath: Path | null;      // the path from start building to end building  FIXME
}

class PathFinder extends Component<PathFinderProps, PathFinderState> {
    constructor(props: PathFinderProps) {
        super(props);
        this.state = {
            currPath: null,
        }
    }

    findPath = async () => {
        try {
            let response = await fetch("http://localhost:4567/findPath?start=" + this.props.startNode + "&end=" + this.props.endNode);
            if (!response.ok) {
                alert("The status is wrong! Expected 200, Was: " + response.status + "\nPlease check you've selected valid START/END node");
                return;
            }
            let path = await response.json() as Path;
            this.setState({
                currPath: path
            })
            if (this.state.currPath !== null) {
                this.props.onChange(this.state.currPath);
            }
        } catch (e) {
            alert("There was an error contacting the server.");
            console.log(e);
        }
    }

    render() {
        return (
            <div id="path-finder">
                <br/>
                <button id="pathFinderBtn" onClick={this.findPath}>FIND THE PATH</button>
            </div>
        );
    }
}

export default PathFinder;