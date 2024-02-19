package com.sisgpn.backend.api.controller;

import com.sisgpn.backend.api.model.Distrito;
import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.model.Vehiculo;
import com.sisgpn.backend.api.service.IDistritoService;
import com.sisgpn.backend.api.service.IVehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    @GetMapping("/vertodo")
    public List<Vehiculo> vertodo(){
        return vehiculoService.findAll();
    }

    @GetMapping("/vertodo/page/{page}")
    public Page<Vehiculo> vertodo(@PathVariable Integer page){
        return vehiculoService.findAll(PageRequest.of(page,5));
    }

    @GetMapping("/ver/{idVehiculo}")
    public ResponseEntity<?> buscarId(@PathVariable Long idVehiculo){
        Vehiculo vehiculo=null;

        Map<String, Object> response = new HashMap<>();

        try {
            vehiculo= vehiculoService.findById(idVehiculo);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al consultar la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (vehiculo == null){
            response.put("mensaje", "Distrito con el ID ".concat(idVehiculo.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Vehiculo>(vehiculo, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody  Vehiculo vehiculo, BindingResult result){

        Vehiculo vehiculoCreado= null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errores =result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            vehiculoCreado = vehiculoService.save(vehiculo);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al guardar en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro creado con éxito");
        response.put("vehiculo", vehiculoCreado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{idVehiculo}")
    public ResponseEntity<?> editar(@Valid @RequestBody Vehiculo vehiculo,BindingResult result, @PathVariable Long idVehiculo){
        Vehiculo vehiculoActual = vehiculoService.findById(idVehiculo);
        Vehiculo vehiculoActualizado= null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errores =result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        if (vehiculoActual == null){
            response.put("mensaje", "Error: No se puede editar. Persona con el ID ".concat(idVehiculo.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            vehiculoActual.setPlaca(vehiculo.getPlaca());
            vehiculoActual.setChasis(vehiculo.getChasis());
            vehiculoActual.setMarca(vehiculo.getMarca());
            vehiculoActual.setModelo(vehiculo.getModelo());
            vehiculoActual.setMotor(vehiculo.getMotor());
            vehiculoActual.setKilometraje(vehiculo.getKilometraje());
            vehiculoActual.setCilindraje(vehiculo.getCilindraje());
            vehiculoActual.setCapacidadCarga(vehiculo.getCapacidadCarga());
            vehiculoActual.setCantidadPasajeros(vehiculo.getCantidadPasajeros());
            vehiculoActual.setTipoVehiculo(vehiculo.getTipoVehiculo());
            vehiculoActual.setObservaciones(vehiculo.getObservaciones());

            vehiculoActualizado=vehiculoService.save(vehiculoActual);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar registro en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Registro actualizado con éxito");
        response.put("distrito", vehiculoActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED) ;
    }

    @DeleteMapping("/eliminar/{idVehiculo}")
    public ResponseEntity<?> eliminar(@PathVariable Long idVehiculo){
        Map<String, Object> response = new HashMap<>();
        try {
            vehiculoService.delete(idVehiculo);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar registro en la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK) ;
    }

}
