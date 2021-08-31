package model;

public class Author  implements  Comparable<Author>{
    private String authorID;
    private String name;

    public Author(String authorID, String name) {
        this.authorID = authorID;
        this.name = name;
    }

    public Author(String authorID) {
        this.authorID = authorID;
    }

    public Author() {
    }
    
    

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public void showAuthor(){
        String msg;
        msg = String.format("|%-6s|%-20s|",
                authorID, name);
        System.out.println(msg);

    }

    @Override
    public int compareTo(Author o) {
      return this.authorID.compareToIgnoreCase(o.authorID);
    }
    
}
