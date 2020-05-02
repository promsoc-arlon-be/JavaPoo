
# How to

* Create the DB with create-db.sh
* Create the schema with restaurant.sql
* Run the Restaurant Java class

# Sample execution:

```
May 02, 2020 2:27:46 PM Restaurant createMenu
INFO: Entering createMenu
Saving plat : Menu starter 1588422467140
May 02, 2020 2:27:47 PM AbstractPlat save
INFO: Inserting dessert  : Menu starter 1588422467140
Saving plat : Menu pp 1588422467533
May 02, 2020 2:27:47 PM AbstractPlat save
INFO: Inserting dessert  : Menu pp 1588422467533
Saving plat : Menu dessert 1588422467555
May 02, 2020 2:27:47 PM AbstractPlat save
INFO: Inserting dessert  : Menu dessert 1588422467555
May 02, 2020 2:27:47 PM Menu save
INFO: Saving menu : My menu
Menu[id=2,	 name=My menu
	Entree[id=2,	 name=Menu starter 1588422467140,	 platId=4]
	PlatPrincipal[id=2,	 name=Menu pp 1588422467533,	 platId=5]
	Dessert[id=2,	 name=Menu dessert 1588422467555,	 anniversary=true,	 platId=6]
]
May 02, 2020 2:27:47 PM AbstractPlat loadPlat
INFO: Load the plat using the plat id: 4
May 02, 2020 2:27:47 PM AbstractPlat loadPlat
INFO: AP: Entree[id=2,	 name=Menu starter 1588422467140,	 platId=4]
May 02, 2020 2:27:47 PM AbstractPlat loadPlat
INFO: Load the plat using the plat id: 5
May 02, 2020 2:27:47 PM AbstractPlat loadPlat
INFO: AP: PlatPrincipal[id=2,	 name=Menu pp 1588422467533,	 platId=5]
May 02, 2020 2:27:47 PM AbstractPlat loadPlat
INFO: Load the plat using the plat id: 6
May 02, 2020 2:27:47 PM AbstractPlat loadPlat
INFO: AP: Dessert[id=2,	 name=Menu dessert 1588422467555,	 anniversary=true,	 platId=6]
Menu[id=2,	 name=My menu
	Entree[id=2,	 name=Menu starter 1588422467140,	 platId=4]
	PlatPrincipal[id=2,	 name=Menu pp 1588422467533,	 platId=5]
	Dessert[id=2,	 name=Menu dessert 1588422467555,	 anniversary=true,	 platId=6]
]
```