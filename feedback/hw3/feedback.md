### Written Answers: 5/6

### Code Quality: 3/3

### Mechanics: 3/3

### General Feedback

### Specific Feedback

# Question 1

The fix n < 2 belongs as a fix for testInductiveCase instead

# RandomHello

When selecting a greeting in RandomHello, the best style would use the length
of the array to specify the maximum value for the random integer generation:
String nextGreeting = greetings[rand.nextInt(greetings.length)];
Notice how this benefits us later on if we wanted to change the number of
possible greetings in the array.

# BallContainer

Your BallContainer add/remove methods are more complicated than they need to be.
Look at the documentation for Set.add and Set.remove and see whether you
need to explicitly handle cases the cases of adding something that already
exists in the set and removing something that doesn't exist in the set.


