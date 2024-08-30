GET - /api/citizens -> Fetch all the citizens
 
POST - /api/citizen -> Add citizen to the citizen table
 
GET - /api/citizen/{id} -> Fetch citizen of particular id
 
DELETE - /api/citizen/{id} -> Delete citizen of particular id
 
PUT - /api/citizen -> Update citizen
 
GET - /api/citizens/firstdose -> Fetch all the citizens who took first dose
GET - /api/citizens/seconddose -> Fetch all the citizens who took second dose
GET - /api/citizens/boosterdose -> Fetch all the citizens who took booster dose
 
GET - /api/citizen/pendingfirstdose -> Fetch List of citizens whose first dose is pending.
GET - /api/citizen/pendingseconddose -> Fetch List of Citizens whose second dose is pending. (120 days+ already over and first dose taken)
GET - /api/citizen/pendingboosterdose -> Fetch List of Citizens whose booster dose is pending (150 days+ already over and second dose taken)
 
POST - /api/addfirstdose -> Add First Dose data
POST - /api/addseconddose -> Add Second Dose data
POST - /api/addboosterdose -> Add Booster Dose data
 
 
GET - /api/firstdoses -> Fetch Data of First Dose Data Table
GET - /api/seconddoses -> Fetch Data of Second Dose Data Table
GET - /api/boosterdoses -> Fetch Data of Booster Dose Data Table
 
GET - /api/citizen/{id}/status -> Get status of citizen - returns string (Vaccination Status)