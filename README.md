To run cd into project root directory /dropwizard-maze-solver and type:

    java -jar target/maze-solver-0.0.1-SNAPSHOT.jar server mazesolver.yml

Use the following CURL command to post data to the resource:

	curl localhost:8000/lab-escape -H 'Content-Type: application/json' -X POST -d '{ "startX":1, "startY":1, "labyrinth": [["O", "O", "O", "O"], ["O"," "," ","O"], ["O"," ","O","O"], ["O"," "," ","O"] ] }'

You should receive back the solved labyrinth in JSON format.

Example input

	OOOOOOOOOO
	O    O   O
	O OO O O O
	O  O O O O
	O OO   O  
	O OOOOOOOO
	O        O
	OOOOOOOOOO
	
Starting point: (x=3, y=1)
 	
 	OOOOOOOOOO
	O    O   O
	O OO O O O
	O• O O O O
	O OO   O  
	O OOOOOOOO
	O        O
	OOOOOOOOOO
	
Desired output:
	
	OOOOOOOOOO
	O••••O•••O
	O•OO•O•O•O
	O• O•O•O•O
	O OO•••O••
	O OOOOOOOO
	O        O
	OOOOOOOOOO
