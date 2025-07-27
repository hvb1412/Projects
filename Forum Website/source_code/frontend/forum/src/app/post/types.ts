export interface Tag {
  id: number;
  name: string;
}

export interface User {
  user_id: number;
  user_name: string;
}
export interface Post {
  post_id: number;
  title: string;
  content: string;
  status: string;
  created_at: string;
  updated_at: string;
  author_name: string;
  categories: string[];
  tags: string[];
}

export interface Comment {
  comment_id: number;
  user_name: string;
  content: string;
  parent_id: number | null;
  created_at: string;
  replies?: Comment[];
}