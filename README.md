# appointment-creation-service

## Development Getting Start
### Build and run the application
```sh
	gradle build & java -jar build/libs/appointment-booking-service-0.1.0.jar
```

## Examples
- Create a new customer endpoint
```sh
	curl -X POST localhost:8080/customers -H 'Content-type:application/json' -d '{"name": "Customer 1", "email": "customer@test.com"}'
```

- Create a new stylist endpoint
```sh
	curl -X POST localhost:8080/stylists -H 'Content-type:application/json' -d '{"name": "Stylist 1", "email": "stylist@test.com"}'
```

- List available slots
http://localhost:8080/appointments/available

- List all customers
http://localhost:8080/customers

- List all stylists
http://localhost:8080/stylists

- Initialize stylist calendar for the next X days (e.g 3)
```sh
	curl -X POST "localhost:8080/stylists/4/init-free-slots?days=3" -H 'Content-type:application/json'
```

- Book an appointment endpoint
```sh
	curl -X POST localhost:8080/appointments/book -H 'Content-type:application/json' -d '{"customerId": 1, "date": "2019-02-14", "fromSlot": "09:00"}'
``

