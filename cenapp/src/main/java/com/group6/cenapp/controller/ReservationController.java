package com.group6.cenapp.controller;


import com.group6.cenapp.model.Reservation;
import com.group6.cenapp.model.User;
import com.group6.cenapp.repository.UserRepository;
import com.group6.cenapp.services.RestaurantService;
import com.group6.cenapp.services.ReservationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Api(tags="Reservations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/reservations")
@Transactional
public class ReservationController  {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantService tableService;

    @GetMapping
    public ResponseEntity<List<Reservation>> listarResrevas() {
        return ResponseEntity.ok(reservationService.getAllReservation());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Reservation>> findAllByUserId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(reservationService.findByUser_id(id), HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Reservation>> findAllByRestaurantId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(reservationService.findByRestaurant_id(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> buscarReserva(@PathVariable Integer id) {
        Optional<Reservation> reservaBuscada = reservationService.getReservationById(id);
        if (reservaBuscada.isPresent()) {
            return ResponseEntity.ok(reservaBuscada.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Reservation reservation)  {
        User user = userRepository.findById(reservation.getUser().getId()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Usuario no se encuentra debe Ingresar o Registrarse  para poder hacer una reserva ");
        } else {
            return ResponseEntity.ok(reservationService.saveReservation(reservation));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> editarReserva (@RequestBody Reservation reservation) {


        Optional<Reservation> reservaBuscada = reservationService.getReservationById(reservation.getId());
        if (reservaBuscada.isPresent()) {
            reservationService.updateReservation(reservation);
            return ResponseEntity.ok("Se Actualizo su  reserva con ID: " + reservation.getId());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Reserva con ID: " + reservation.getId() + " no se encuentra ");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        if(reservationService.getReservationById(id).isPresent()){
            reservationService.deleteReservationById(id);
            return ResponseEntity.ok("Se eliminó con éxito la reserva con ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la reserva con ID: " + id);
    }

}
