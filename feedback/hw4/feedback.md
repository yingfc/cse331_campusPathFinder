### Written Answers: 18/26
0.1: You should include a loop invariant for your pseudocode.

0.2: You should include a loop invariant for your pseudocode.

0.2: The loop in polynomial division needs to terminate when p becomes 0.  If you
don't have this condition in the loop, then an infinite loop can occur when the
degree of the divisor is 0, since the degree of p = 0 is also 0.

0.2: You have not defined subtraction or term by poly multiplication, so you cannot use those operations in your pseudocode.

2.3: You would also need to modify `checkRep`.

1.3, 2.1, 3.1: RatTerm and RatNum only need checkReps in constructors because Java's compiler guarantees that the state won't be modified. this is because the fields are declared as `final` AND the field types are immutable (int, RatNum). Even though RatPoly's list is declared as `final`, the contents inside of the list can still change. Thus, it is necessary to include checkReps at the beginning and end of every public method.

### Code Quality: 3/3

### Mechanics: 3/3

### General Feedback
Excellent coding quality throughout the assignment!

### Specific Feedback
