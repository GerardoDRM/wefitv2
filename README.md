# wefitv2

## Database

**User**
* id int 
* email string
* isActive boolean
* name string

**Exercise**
* id int 
* name
* img 
* video

**Workout**
* id int
* dayOfWeek (Lunes, Martes, Miercoles)
* userId int FK
* exerciseId int FK


**Set**
* id int 
* workoutId FK
* setNumber int
* weight decimal
* repetitions int 



