for i in {1..5..1}
do
    javac -p mods -d puzzles/p${i}solution puzzles/p${i}solution/euler/puzzles/p${i}solution/*.java puzzles/p${i}solution/euler/puzzles/p${i}solution/strategy/*.java  puzzles/p${i}solution/module-info.java && 
    jar -cvf mods/euler.puzzles.p${i}solution.jar -C puzzles/p${i}solution/ . 
done
echo "done!"