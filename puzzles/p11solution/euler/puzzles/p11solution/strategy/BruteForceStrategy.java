package euler.puzzles.p11solution.strategy;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;


/*
*
*   Will start at left-top corner and try all 'unique' directions,
*   the other directions will overlap and thus waste time and resources.
*    X--y
*   /|\
*  / | \
* y  y  y
*
* If one index is outOfBound, the path will cancel and move to the next
* */



public class BruteForceStrategy implements IStrategy {
    private final static int PATH_LENGTH = 4;

    public long run(final int[][] grid) {
        GridIterator iterator = GridIterator.start(grid);

        return iterator.stream()
                .mapToLong(this::BestAdjacentPath)
                .max()
                .orElse(-1);
    }

    private long BestAdjacentPath(GridIterator iterator) {
        // Check all type of directions once
        return Stream.of(
            GridIndex.of(-1, -1), // diagonal left-down
            GridIndex.of(-1, 0),  // vertical down
            GridIndex.of(-1, 1),  // diagonal right-down
            GridIndex.of(0, 1)    // horizontal right
        )
            .mapToLong(direction -> this.calculateAdjacentPath(iterator, direction))
            .max().orElse(-1);
    }
    private long calculateAdjacentPath(GridIterator iterator, GridIndex direction) {
        return Stream.iterate(iterator, Objects::nonNull, i -> i.moveToRelativePos(direction))
                .limit(PATH_LENGTH)
                .mapToLong(GridIterator::value)
                .reduce(1, (a,b) -> a * b);
    }
}

record GridIterator(int[][] grid, GridIndex cell) implements Iterator<GridIterator> {

    public static GridIterator start(int[][] grid) {
        return new GridIterator(grid, new GridIndex(0,0));
    }

    @Override
    public boolean hasNext() {
        return !(isLastColumn() && isLastRow());
    }

    @Override
    public GridIterator next() {
        if(!this.hasNext()) return null;

        if (isLastColumn()) {
            return new GridIterator(grid(), GridIndex.of(this.cell.row() + 1, 0));
        }

        return new GridIterator(grid(), GridIndex.of(this.cell.row(), this.cell.column() + 1));
    }

    public long value(){
        return grid[cell.row()][cell.column()];
    }

    public GridIterator moveToRelativePos(GridIndex direction) {
        GridIndex newPos = this.cell().moveToRelativeIndex(direction);

        return (indexOutOfBound(newPos)) ? null : new GridIterator(grid, newPos);
    }

    public boolean indexOutOfBound(GridIndex pos) {
        if(pos.row() < 0 || pos.row() >= this.grid.length) return true;
        if(pos.column() < 0 || pos.column() >= this.grid[pos.row()].length) return true;

        return false;
    }

    public boolean isLastRow() {
        return cell.row() == grid.length - 1;
    }

    public boolean isLastColumn() {
        return cell.column() == grid[cell.row()].length - 1;
    }

    public Stream<GridIterator> stream() {
        return Stream.iterate(this
                , Objects::nonNull
                , GridIterator::next
        );
    }
}

record GridIndex(int row, int column) {
    public static GridIndex of(int row, int column) {
        return new GridIndex(row, column);
    }

    public GridIndex moveToRelativeIndex(GridIndex direction) {
        return new GridIndex(
                row() + direction.row(),
                column() + direction.column()
        );
    }
}