package ru.netology.repository;

import ru.netology.model.Post;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {

  private final ConcurrentHashMap<Long,Post> posts;
  private final AtomicLong idCounter = new AtomicLong();

  public PostRepository () {
    this.posts = new ConcurrentHashMap<>();
  }

  public Collection<Post> all() {
    return posts.values();
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Post post) {
    if(post.getId() == 0) {
      long id = idCounter.incrementAndGet();
      post.setId(id);
      posts.put(id, post);
    } else if (post.getId() != 0){
      Long currentId = post.getId();
      posts.put(currentId, post);
    }
    return post;
  }

  public void removeById(long id) {
    posts.remove(id);
  }
}
