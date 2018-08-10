package main.java.utils;

/**
 * Website information class with simple setters and getters to retrieve information parsed from URLs
 */
public class WebsiteInformation {
    int rank;
    String url;
    int linkingRootDomain;
    int externalLink;
    double mozRank;
    double mozTrust;

    public WebsiteInformation(int rank, String url, int linkingRootDomain, int externalLink, double mozRank, double mozTrust){
        this.url = url;
        this.rank  = rank;
        this.linkingRootDomain = linkingRootDomain;
        this.externalLink = externalLink;
        this.mozRank = mozRank;
        this.mozTrust = mozTrust;
    }

    public int getRank() {
        return rank;
    }

    public String getUrl() {
        return url;
    }

    public int getLinkingRootDomain() {
        return linkingRootDomain;
    }

    public int getExternalLink() {
        return externalLink;
    }

    public double getMozRank() {
        return mozRank;
    }

    public double getMozTrust() {
        return mozTrust;
    }
}
