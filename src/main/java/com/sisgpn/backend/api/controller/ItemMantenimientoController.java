package com.sisgpn.backend.api.controller;

import com.sisgpn.backend.api.model.ItemMantenimiento;
import com.sisgpn.backend.api.model.Mantenimiento;
import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.service.IItemMantenimientoService;
import com.sisgpn.backend.api.service.IMantenimientoService;
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
@RequestMapping("/items")
public class ItemMantenimientoController {

    @Autowired
    private IItemMantenimientoService itemMantenimientoService;

    @GetMapping("/vertodo")
    public List<ItemMantenimiento> vertodo(){
        return itemMantenimientoService.findAll();
    }

    @GetMapping("/vertodo/page/{page}")
    public Page<ItemMantenimiento> vertodo(@PathVariable Integer page){
        return itemMantenimientoService.findAll(PageRequest.of(page,5));
    }


    @GetMapping("/ver/{idItem}")
    public ResponseEntity<?> buscarId(@PathVariable Long idItem){
        ItemMantenimiento itemMantenimiento=null;
        Map<String, Object> response = new HashMap<>();
        try {
            itemMantenimiento= itemMantenimientoService.findById(idItem);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al consultar la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (itemMantenimiento == null){
            response.put("mensaje", "Persona con el ID ".concat(idItem.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ItemMantenimiento>(itemMantenimiento, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody  ItemMantenimiento itemMantenimiento, BindingResult result){

        ItemMantenimiento itemMantenimientoCreado= null;
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
            itemMantenimientoCreado = itemMantenimientoService.save(itemMantenimiento);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al guardar en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro creado con éxito");
        response.put("persona", itemMantenimientoCreado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @PutMapping("/editar/{idItem}")
    public ResponseEntity<?> editar(@Valid @RequestBody ItemMantenimiento itemMantenimiento, BindingResult result, @PathVariable Long idItem){
        ItemMantenimiento itemActual = itemMantenimientoService.findById(idItem);
        ItemMantenimiento itemActualizado= null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errores =result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errores", errores);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        if (itemActual == null){
            response.put("mensaje", "Error: No se puede editar. Persona con el ID ".concat(idItem.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            itemActual.setCodigoItem(itemMantenimiento.getCodigoItem());
            itemActual.setNombreItem(itemMantenimiento.getNombreItem());
            itemActual.setDescripcionItem(itemMantenimiento.getDescripcionItem());
            itemActual.setMantenimiento(itemMantenimiento.getMantenimiento());
            itemActualizado=itemMantenimientoService.save(itemActual);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar registro en la Base de Datos");
            response.put("errores", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Registro actualizado con éxito");
        response.put("persona", itemActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED) ;
    }

    @DeleteMapping("/eliminar/{idItem}")
    public ResponseEntity<?> eliminar(@PathVariable Long idItem){
        Map<String, Object> response = new HashMap<>();
        try {
            itemMantenimientoService.delete(idItem);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar registro en la Base de Datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK) ;

    }


    @GetMapping("/mantenimientos")
    public List<Mantenimiento> listarMantenimientos(){
        return itemMantenimientoService.findAllMantenimientos();
    }

}
