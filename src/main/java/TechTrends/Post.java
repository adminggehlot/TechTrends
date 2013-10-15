package TechTrends;

import lombok.Value;

import java.util.Date;

@Value
public class Post {
    String author;
    String body;
    Date date;

    public static class PostBuilder {
        private Post post;
        private String author;
        private String body;
        private Date date;

        public PostBuilder() {
        }

        public PostBuilder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public PostBuilder withBody(String body) {
            this.body = body;
            return this;
        }

        public PostBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Post build() {
            post = new Post(this.author, this.body, this.date);
            return post;
        }
    }
}
