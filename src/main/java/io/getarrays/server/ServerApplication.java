package io.getarrays.server;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepository;

import org.springframework.web.filter.CorsFilter;
//INSTEAD import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.filter.CorsFilter;


import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository) {
		return args -> {
			/*String[] ips = {
					"192.168.1.160", "192.168.1.60", "192.168.1.30", "192.168.1.10", "192.168.1.50", "192.168.1.20", "192.168.1.70",
					"192.168.1.110", "192.168.1.90", "192.168.1.130", "192.168.1.100", "192.168.1.40", "192.168.1.80", "192.168.1.120"
			};
			String[] os = {
					"Ubuntu Linux", "Fedora Linux", "MS 2008", "Red Hat Enterprise Linux", "Windows Server 2019", "CentOS Linux", "Debian Linux",
					"Ubuntu Server", "Windows Server 2016", "Fedora Linux", "MS 2012 R2", "Red Hat Enterprise Linux", "Ubuntu Linux", "Windows Server 2016"
			};
			String[] ram = { "16 GB", "32 GB", "16 GB", "64 GB", "32 GB", "8 GB", "16 GB", "64 GB", "32 GB", "16 GB", "32 GB", "16 GB", "8 GB", "64 GB" };
			String[] type = {
					"Personal PC", "Dell Tower", "Web Server", "Mail Server", "Database Server", "File Server", "Application Server",
					"Virtualization Server", "Print Server", "Backup Server", "Domain Controller", "Proxy Server", "Development Server", "Game Server"
			};
			String[] imgUrls = {
					"http://localhost:8080/server/ServerImgIcons/server1.png", "http://localhost:8080/server/ServerImgIcons/server2.png",
					"http://localhost:8080/server/ServerImgIcons/server3.png", "http://localhost:8080/server/ServerImgIcons/server4.png",
					"http://localhost:8080/server/ServerImgIcons/server5.png", "http://localhost:8080/server/ServerImgIcons/server6.png",
					"http://localhost:8080/server/ServerImgIcons/server7.png", "http://localhost:8080/server/ServerImgIcons/server8.png",
					"http://localhost:8080/server/ServerImgIcons/server9.png", "http://localhost:8080/server/ServerImgIcons/server10.png",
					"http://localhost:8080/server/ServerImgIcons/server11.png", "http://localhost:8080/server/ServerImgIcons/server12.png",
					"http://localhost:8080/server/ServerImgIcons/server13.png", "http://localhost:8080/server/ServerImgIcons/server14.png"
			};

			/*for (int i = 0; i < ips.length; i++) {
				Status status = (i % 2 == 0) ? Status.SERVER_UP : Status.SERVER_DOWN;
				serverRepository.save(new Server(null, ips[i], os[i], ram[i], type[i], imgUrls[i], status));
			}*/
			//for (int i = 0; i < ips.length; i++) serverRepository.save(new Server(null, ips[i], os[i], ram[i], type[i], imgUrls[i], Status.SERVER_UP));


			serverRepository.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/ServerImgIcons/server1.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.60", "Fedora Linux", "32 GB", "Dell Tower", "http://localhost:8080/server/ServerImgIcons/server2.png", Status.SERVER_DOWN));
			serverRepository.save(new Server(null, "192.168.1.30", "MS 2008", "16 GB", "Web Server", "http://localhost:8080/server/ServerImgIcons/server3.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.10", "Red Hat Enterprise Linux", "64 GB", "Mail Server", "http://localhost:8080/server/ServerImgIcons/server4.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.50", "Windows Server 2019", "32 GB", "Database Server", "http://localhost:8080/server/ServerImgIcons/server5.png", Status.SERVER_DOWN));
			serverRepository.save(new Server(null, "192.168.1.20", "CentOS Linux", "8 GB", "File Server", "http://localhost:8080/server/ServerImgIcons/server6.png", Status.SERVER_DOWN));
			serverRepository.save(new Server(null, "192.168.1.70", "Debian Linux", "16 GB", "Application Server", "http://localhost:8080/server/ServerImgIcons/server7.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.110", "Ubuntu Server", "64 GB", "Virtualization Server", "http://localhost:8080/server/ServerImgIcons/server8.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.90", "Windows Server 2016", "32 GB", "Print Server", "http://localhost:8080/server/ServerImgIcons/server9.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.130", "Fedora Linux", "16 GB", "Backup Server", "http://localhost:8080/server/ServerImgIcons/server10.png", Status.SERVER_DOWN));
			serverRepository.save(new Server(null, "192.168.1.100", "MS 2012 R2", "32 GB", "Domain Controller", "http://localhost:8080/server/ServerImgIcons/server11.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.40", "Red Hat Enterprise Linux", "16 GB", "Proxy Server", "http://localhost:8080/server/ServerImgIcons/server12.png", Status.SERVER_DOWN));
			serverRepository.save(new Server(null, "192.168.1.80", "Ubuntu Linux", "8 GB", "Development Server", "http://localhost:8080/server/ServerImgIcons/server7.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.120", "Windows Server 2016", "64 GB", "Game Server", "http://localhost:8080/server/ServerImgIcons/server3.png", Status.SERVER_UP));


		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList(
				"Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Request-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"
		));
		corsConfiguration.setExposedHeaders(Arrays.asList(
				"Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"
		));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
		//return new CorsFilter();

	}
}
