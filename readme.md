## Disclaimer!

As said on the website, if you participate in the euler project *do not read these answers*, there is no fun in the puzzles if you google the answers. Besides I have no idea if these answers are any good. 
This is just for me to learn Java.

# package contracts
javac -p mods -d contract contract/euler/contract/*.java
jar -cvf mods/euler.contract.jar -C contract/ .

# package runner
javac -p mods -d runner runner/euler/runner/*.java runner/module-info.java
jar -cvf mods/euler.runner.jar -C runner/ .  

# run runner
java -p mods -m euler.runner/euler.runner.Main

# build.sh
Small bash file to compile all solutions into modules