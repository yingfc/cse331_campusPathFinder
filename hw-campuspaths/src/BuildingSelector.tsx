import React, {Component} from "react";

interface BuildingSelectorState {
    allInfo: JSON | null;       // all building information to display as select options
    startNode: string;  // the start building
    endNode: string;    // the end building
}

interface BuildingSelectorProps {
    onStartChange(newNode: string): void;   // called when a new start building is selected
    onEndChange(newNode: string): void;     // called when a new end building is selected
    onResetClick(): void;       // called when the reset button is clicked
}

class BuildingSelector extends Component<BuildingSelectorProps, BuildingSelectorState> {

    constructor(props: BuildingSelectorProps) {
        super(props);
        this.state = {
            allInfo: null,
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

    componentDidMount() {
        this.listBuilding();
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
            startNode: "",
            endNode: "",
        })
        this.props.onResetClick();
    }

    render() {
        const options = [];
        if (this.state.allInfo !== null) {
            const buildingShortName = Object.keys(this.state.allInfo);
            for (let i = 0; i < buildingShortName.length; i++) {
                options.push(<option key={i}>{buildingShortName[i]}</option>)
            }
        }
        return (
            <div id="building-selector">
                <br/>
                <div id="selector">
                    <select onChange={this.onSelectStartChange} value={this.state.startNode}>
                        <option>--- select START building ---</option>
                        {options}
                    </select>

                    <select onChange={this.onSelectEndChange} value={this.state.endNode}>
                        <option>--- select END building ---</option>
                        {options}
                    </select>
                </div>
                <br/>
                <button id="reset-btn" onClick={this.onResetBtnClick}>RESET</button>
                <br/>
            </div>
        );
    }
}

export default BuildingSelector;