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
import Map from "./Map";
import BuildingSelector from "./BuildingSelector";
import PathFinder from "./PathFinder";

interface AppState {
    sourceNodeName: string;     // the name of the source building
    destNodeName: string;       // the name of the destination building
    path: any;                  // the path between the two buildings FIXME
}

class App extends Component<{}, AppState> {

    constructor(props: {}) {
        super(props);
        this.state = {
            sourceNodeName: "",
            destNodeName: "",
            path: "",
        }
    }

    updateStartNode = (newNode: string) => {
        this.setState({
            sourceNodeName: newNode
        })
    }

    updateEndNode = (newNode: string) => {
        this.setState({
            destNodeName: newNode
        })
    }

    drawPath = (newPath: any) => {
        this.setState({
            path: newPath
        })
    }

    onResetButton = () => {
        this.setState({
            sourceNodeName: "",
            destNodeName: "",
            path: "",
        })
    }

    render() {
        return (
            <div>
                <p>UW Ultimate CampusPaths Finding Tool!</p>
                <Map path={this.state.path}/>
                <BuildingSelector onStartChange={this.updateStartNode} onEndChange={this.updateEndNode} onResetClick={this.onResetButton}/>
                <PathFinder startNode={this.state.sourceNodeName} endNode={this.state.destNodeName} onChange={this.drawPath}/>
            </div>
        );
    }

}

export default App;
