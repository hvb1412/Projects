export interface Post {
  post_id: number;
  title: string;
  content: string;
  created_at: string;
  author_name: string;
  category_id: number;
  tags: { tag_id: number; name: string }[];
}

export interface User {
  user_id: number;
  user_name: string;
}
