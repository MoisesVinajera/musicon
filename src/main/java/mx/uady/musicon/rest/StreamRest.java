package mx.uady.musicon.rest;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.musicon.service.StreamService;

@RestController
@RequestMapping("/api")
public class StreamRest {

    @Autowired
    private StreamService streamService;

    @GetMapping("/songs/streaming/{name}/complete")
    public ResponseEntity<UrlResource> getSong(@PathVariable String name) throws MalformedURLException {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaTypeFactory.getMediaType(streamService.getSongComplete(name)).orElse(MediaType.APPLICATION_OCTET_STREAM)) 
                .body(streamService.getSongComplete(name));
    }

    @GetMapping("/songs/streaming/{name}")
    public ResponseEntity<ResourceRegion> getSongByParts(@PathVariable String name,
            @RequestHeader HttpHeaders headers) throws IOException {

        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType((UrlResource)streamService.getSongByParts(name, headers).get(0))
                .orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body((ResourceRegion)streamService.getSongByParts(name, headers).get(1));
    }

}
