package io.getarrays.server.resource;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Response;
import io.getarrays.server.model.Server;
import io.getarrays.server.service.implementation.ServerServiceImplementation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDateTime.now;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

/*POSTMAN CRUD examples

  1)  GET http://localhost:8080/server/list
  2)  GET http://localhost:8080/server/get/3
  3)  POST http://localhost:8080/server/save            //add
        {
            "serverId": 15,
            "ipAddress": "192.168.1.300",
            "serverName": "VanillaOS",
            "serverMemory": "64 GB",
            "type": "Development Server",
            "status": "SERVER_UP"
        }
   4) GET http://localhost:8080/server/ServerImgIcons/server1.png
   5) GET http://localhost:8080/server/ping/192.168.1.160               //ping
   6)

 */


@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImplementation serverServiceImplementation;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverServiceImplementation.list(30)))
                        .message("Servers retrieved")
                        .status(HttpStatus.OK)
                        .status(HttpStatus.valueOf(HttpStatus.OK.value()))
                        .build()
        );
    }


    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress)   throws IOException   {
        Server server = serverServiceImplementation.ping(ipAddress);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
                        .status(HttpStatus.OK)
                        .status(HttpStatus.valueOf(HttpStatus.OK.value()))
                        .build()
        );
    }


    @PostMapping("/save")
    public ResponseEntity<Response> pingServer(@RequestBody @Valid Server server) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverServiceImplementation.create(server)))
                        .message("Server successfully created")
                        .status(HttpStatus.CREATED)
                        .status(HttpStatus.valueOf(HttpStatus.CREATED.value()))
                        .build()
        );
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverServiceImplementation.get(id)))
                        .message("Server retrieved")
                        .status(HttpStatus.OK)
                        .status(HttpStatus.valueOf(HttpStatus.OK.value()))
                        .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted", serverServiceImplementation.delete(id)))
                        .message("Server deleted")
                        .status(HttpStatus.OK)
                        .status(HttpStatus.valueOf(HttpStatus.OK.value()))
                        .build()
        );
    }

    @GetMapping(path = "/ServerImgIcons/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {

        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/ServerImgIcons/" + fileName));
    }


}
