# FreezingChessBoard

### A Solution to the N-Queens problem using the Simulated Annealing (SA) local search algorithm

The main constraint that I kept in mind during the development was to only put one queen in each row.

Initially a random state is generated regarding the constraint.

Afterwards a loop is initiated.

A random successor of the current state is generated.

If the number of pairs of queens checking each other is more than the successor (father worse) the state changes to the successor, Otherwise the move is only taken by the following chance:

e^((currentFitness – successorFitness-0.1)/T)


Where T is initially set as 0.1 and decreased -0.0001 by each move.

The -0.1 in the superscript is placed to avoid 0 value such that the probability would be 1.

The implementation of the chance here is by using a random number between 0 and 1 and only taking the move if the number generated is < probability number.
