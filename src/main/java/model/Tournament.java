package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Tourneyplan")
@NamedQueries({

        @NamedQuery(name = "findtAllTournameName", query = "SELECT c FROM  Tournament c"),
        @NamedQuery(name = "src.main.model.findTournamentbyDate", query = "SELECT c FROM  Tournament c WHERE  c.vonDate >= :VonDate AND c.vonDate <= :BisDate"),
        @NamedQuery(name = "src.main.model.findbyID", query = "DELETE FROM  Tournament c WHERE c.id = :TourneyplanID"),
        @NamedQuery(name = "src.main.model.findBisdDate", query = "SELECT c FROM  Tournament c WHERE c.id = :TourneyplanID")

})

public class Tournament
{

    @Setter
    @Getter
    @Id
    @Column(name = "TourneyplanID", nullable = false)
    @NotNull
    private  int id;


    @Setter
    @Getter
    @Column(name = "Titel")
    @JsonProperty
    private String tournamentName;


    @Setter
    @Getter
    @Column(name = "Ort")
    @JsonProperty
    private String tournamentVenues;


    @Setter
    @Getter
    @Column(name = "VonDate")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty
    private String vonDate;


    @Setter
    @Getter
    @Column(name = "BisDate")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty
    private String bisDate;



    @Setter
    @Getter
    @Column(name = "Link")
    @JsonProperty
    private String link;

    public Tournament(){

    }

    public Tournament(int id,String name, String venues, String datestart, String end, String link) {
        this.id = id;
        this.tournamentName = name;
        this.tournamentVenues = venues;
        this.vonDate = datestart;
        this.bisDate = end;
        this.link = link;
    }


    @Override
    public String toString()
    {
        return "Tournament{" + "id=" + id +", tournamentName=' " + tournamentName + '\'' + "tournamentvenues: "+ tournamentVenues+ '\'' + "vonDate: " + vonDate + '\''  + "BisDate: " + bisDate + '\'' +"Link:  " + link + '}';
    }



}
