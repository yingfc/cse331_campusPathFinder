import React, {Component} from "react";

interface BuildingSelectorState {
    allInfo: any;
    startNode: string;
    endNode: string;
}

interface BuildingSelectorProps {
    onStartChange(newNode: string): void;
    onEndChange(newNode: string): void;
    onResetClick(): void;
}

class BuildingSelector extends Component<BuildingSelectorProps, BuildingSelectorState> {

    constructor(props: BuildingSelectorProps) {
        super(props);
        this.state = {
            allInfo: "",
            startNode: "",
            endNode: "",
        }
    }

    listBuilding = async () => {
        try {
            let response = await fetch("http://localhost:4567/listBuilding");
            if (!response.ok) {
                alert("The status is wrong! Expected 200, Was: " + response.status);
                return;
            }
            let buildingInfo = await response.json();
            this.setState({
                allInfo: buildingInfo
            })
        } catch (e) {
            alert("There was an error contacting the server.");
            console.log(e);
        }
    }

    onSelectStartChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const newNode: string = event.target.value;
        this.setState({
            startNode: newNode
        })
        this.props.onStartChange(newNode);
    }

    onSelectEndChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const newNode: string = event.target.value;
        this.setState({
            endNode: newNode
        })
        this.props.onEndChange(newNode);
    }

    onResetBtnClick = () => {
        this.setState({
            allInfo: "",
            startNode: "start",
            endNode: "",
        })
        this.props.onResetClick();
    }

    render() {
        const buildingShortName = Object.keys(this.state.allInfo);
        return (
            <div id="building-selector">
                <br/>
                <button id="start-btn" onClick={this.listBuilding}>Click to START</button>
                <br/>
                <div id="selector">
                    <select onChange={this.onSelectStartChange}>
                        <option>--- select START building ---</option>
                        {
                            buildingShortName.map((x,y) => <option key={y}>{x}</option>)
                        }
                    </select>

                    <select onChange={this.onSelectEndChange}>
                        <option>--- select END building ---</option>
                        {
                            buildingShortName.map((x,y) => <option key={y}>{x}</option>)
                        }
                    </select>
                </div>
                <button id="reset-btn" onClick={this.onResetBtnClick}>RESET</button>
                <br/>
            </div>
        );
    }
}

export default BuildingSelector;