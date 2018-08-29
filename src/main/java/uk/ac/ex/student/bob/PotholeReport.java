package uk.ac.ex.student.bob;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

// Types required for the MongoDb document
import org.bson.Document;
import com.mongodb.client.model.geojson.Position;
import com.mongodb.client.model.geojson.Point;

public class PotholeReport {

    private List<Double> location; // required
    private Date date_reported; // required
    private Date date_observed; // optional
    private String reported_by; // optional
    private Double depth; // optional
    private Double diameter; // optional

    // There are muliple properties to initialise, some of 
    // which are optional, so we use the Builder pattern.
    public static class Builder{

        private List<Double> location; // required
        private Date date_reported; // required
        private Date date_observed; // optional
        private String reported_by; // optional
        private Double depth; // optional
        private Double diameter; // optional

        public Builder( Double longitude, Double latitude ){
            this.location = Arrays.asList(longitude, latitude);
            // date_reported is also required so we set it to 'now',
            // but this value can be modified by chaining withDateReported().
            this.date_reported = new Date();
            // All other parameters are optional so we leave them as null.

            // Builder is a constructor so returns a Builder object (i.e. this)
        }

        // These further methods are chained to set extra parameters.
        // For example
        // PotholeReport sample_report = new 
        //   PotholeReport.Builder(-73.92502,40.8279556)
        //      .withDateReported( new Date() )
        //      .withReportedBy("bob").build();
        //
        public Builder withDateReported( Date date_reported ){
            this.date_reported = date_reported;
            return this;
        }

        public Builder withDateObserved( Date date_observed ){
            this.date_observed = date_observed;
            return this;
        }

        public Builder withReportedBy( String reported_by ){
            this.reported_by = reported_by;
            return this;
        }

        public PotholeReport build(){
            // The PotholeReport constructor is private, but as
            // an internal class we can access it.  However,
            // with great power comes great responsibility. So
            // we must now ensure that any PotholeReport object
            // we create has all the required properties.
            PotholeReport report = new PotholeReport();

            report.location = this.location;
            report.date_reported = this.date_reported;
            report.date_observed = this.date_observed;
            report.reported_by = this.reported_by;
            report.depth = this.depth;
            report.diameter = this.diameter;

            return report;
        }

    }

    // By making the default constructor private we prevent
    // situations where empty reports are created. 
    private PotholeReport(){};


    public Document toDocument(){
            // Always include these properties.
            Document doc = new Document("location", new Point(new Position( location )))
            .append("date reported", date_reported);
            
            // Only include others if not null
            if( reported_by != null){
                doc.append("reported by", reported_by);
            }
            if( date_observed != null){
                doc.append("date observed", date_observed);
            }
            if( depth != null){
                doc.append("depth", depth);
            }
            if( diameter != null){
                doc.append("diameter", diameter);
            }
            return doc;
    }
 }

