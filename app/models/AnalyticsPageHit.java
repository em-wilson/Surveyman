package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class AnalyticsPageHit extends Model {
	public String visitorUuid;
	public String host;
	public String path;
	public String querystring;
}
