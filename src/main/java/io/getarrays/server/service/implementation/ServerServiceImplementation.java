package io.getarrays.server.service.implementation;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepository;
import io.getarrays.server.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class ServerServiceImplementation implements ServerService {
    private final ServerRepository serverRepository;
    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        //log.info("Saving new server: {}", server.getServerName());
        server.setImageUrl(setServerImageUrl().toUriString());

        return serverRepository.save(server);
    }

    private UriComponentsBuilder setServerImageUrl() {
        String[] imageIconsFilesNames = {"server1.png", "server2.png", "server3.png", "server4.png", "server5.png", "server6.png", "server7.png", "server8.png", "server9.png", "server10.png", "server11.png", "server12.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/ServerImgIcons" + imageIconsFilesNames[new Random().nextInt(12)]);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepository.save(server);

        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        //log.info("Updating the server: {}", server.getServerName());
        log.info("Updating the server: {}", server.getName());

        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by id: {}", id);
         serverRepository.deleteById(id);
         return Boolean.TRUE;
    }

}
