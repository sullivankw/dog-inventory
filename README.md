# dog-inventory app

### Starting the App ###  
* D/L the zip version and extract to your preferred location
* You need java installed 
* Ensure nothing else is running on port 8080
* navigate to dog-inventory-master/DogInventory. This dir will contain the json.file that stores dog information and the logging file(s) after the app is started
* Open terminal or command line and execute java -jar {PATH_TO_JAR}. Ctrl C will stop it

### Using the api ###  
* GET and GET/{id} can be easily used from your broswer with http://localhost:8080/dogInventory/dogs
http://localhost:8080/dogInventory/dogs/{id}

*To do a POST you will need a tool such as POSTMAN
* select POST and raw and enter the url  http://localhost:8080/dogInventory/dogs. Hit Send. 

Sample POST http://localhost:8080/dogInventory/dogs

    {
        "name": "spot",
        "ownerName": "dr feelsgood",
        "notes": [
            "slow",
            "stinky",
            "hungry"
        ]
    }

*To do a PUT you must select PUT http://localhost:8080/dogInventory/dogs/{id} where
id is of the Dog you want to edit. To change the owner name and add a new note to the entry above we may try something like this where we already did a GET and retrieved the id

Sample PUT http://localhost:8080/dogInventory/dogs/567445

    {
        "name": "spot",
        "ownerName": "Hal",
        "notes": [
            "slow",
            "stinky",
            "hungry",
            "timid"
        ]
    }