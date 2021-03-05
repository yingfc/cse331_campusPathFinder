import React, {Component} from "react";

interface PathFinderProps {
    startNode: string;
    endNode: string;
    onChange(newPath: JSON): void;
}

interface PathFinderState {
    currPath: any;  // FIXME
}

class PathFinder extends Component<PathFinderProps, PathFinderState> {
    constructor(props: PathFinderProps) {
        super(props);
        this.state = {
            currPath: "",
        }
    }

    findPath = async () => {
        try {
            let response = await fetch("http://localhost:4567/findPath?start=" + this.props.startNode + "&end=" + this.props.endNode);
            if (!response.ok) {
                alert("The status is wrong! Expected 200, Was: " + response.status + "\nPlease check you've selected valid START/END node");
                return;
            }
            let path = await response.json();
            this.setState({
                currPath: path
            })
            this.props.onChange(this.state.currPath);
        } catch (e) {
            alert("There was an error contacting the server.");
            console.log(e);
        }
    }

    render() {
        return (
            <div id="path-finder">
                <br/>
                <button onClick={this.findPath}>FIND THE PATH</button>
            </div>
        );
    }
}

export default PathFinder;