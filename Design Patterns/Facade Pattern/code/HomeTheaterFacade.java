import java.util.ArrayList;
import java.util.List;

public class HomeTheaterFacade {
    private List<Speaker> speakers;
    private Projector projector;
    private StreamingPlayer player;

    HomeTheaterFacade() {
        this.speakers = new ArrayList<>();
        this.projector = new Projector();
        this.player = new StreamingPlayer();

        this.speakers.add(new Speaker());
    }

    public void watchMovie(String movie) {
        System.out.println("--- Preparing Home Theater ---");
        projector.on();
        projector.setAspectRatio(720, 720);
        speakers.forEach(speaker -> speaker.on());
        speakers.forEach(speaker -> speaker.changeVolume(20));
        player.on();
        player.play();
    }
}