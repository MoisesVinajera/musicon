package mx.uady.musicon.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamService {
    
    private static final long KB = 1024;
    private static final long MB = 1024 * 1024*10;

    @Transactional(readOnly = true)
    public UrlResource getSongComplete(String name) throws MalformedURLException{

        String location = "songs/" + name;
        UrlResource song = new UrlResource("file:" + location); // file:videos/fish.mp4
        
        return song;    
    }
    

    @Transactional(readOnly = true)
    public List<Object> getSongByParts(String name,HttpHeaders headers) throws IOException{

        String location = "songs/" + name;
        UrlResource song = new UrlResource("file:" + location);
        ResourceRegion region = splitSong(song, headers);
        
        List<Object> list = new ArrayList<Object>();
        list.add(song);
        list.add(region);
        return list;    
    }

    private ResourceRegion splitSong(UrlResource song, HttpHeaders headers) throws IOException {
        long lenghtSong = song.contentLength();
        HttpRange range = headers.getRange().size() != 0 ? headers.getRange().get(0) : null;

        if(range == null)  {
            long step = Math.min(KB, lenghtSong); 
            return new ResourceRegion(song, 0, step); 
        }

        long start = range.getRangeStart(lenghtSong);
        long end = range.getRangeEnd(lenghtSong);
        long step = Math.min(MB, end - start + 1); 
        return new ResourceRegion(song, start, step); 
        
    }
}
