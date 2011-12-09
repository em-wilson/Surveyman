package models;

import java.util.UUID;
import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class AnalyticsVisitor extends Model {
	public String uuid;
	public String charset;
	public String encoding;
	public String language;
	public String userAgent;
	public String referrer;
	public Date created;
	
	public AnalyticsVisitor()
	{
		this.uuid = UUID.randomUUID().toString();
		this.created = new Date();
	}
}
