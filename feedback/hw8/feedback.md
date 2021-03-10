## Program operation (correctness) and extra credit

### Correctness Score: 3/3

### Extra Credit Score: -/3

#### Program operation/extra credit feedback

None.

## Code quality and implementation

### Design: 3/3

### Code quality: 3/3

### Mechanics: 3/3

#### Code Review Feedback

- For GridSizePicker, the value prop is unused and should be removed

- It would be better to error checking/edge processing in EdgeList and store the processed edges in App's state and pass that as a prop to Grid. This   allows us to only update the state of App and trigger all the rerenders if the edge data inputted by the user is valid, which minimizes unnecessary renders.

