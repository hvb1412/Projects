"use client";

import { useParams, useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import Header from "@/components/Header";
import PostContent from "@/app/post/components/PostContent";
import AuthorInfo from "@/app/post/components/AuthorInfo";
import CommentList from "@/app/post/components/CommentList";
import CommentForm from "@/app/post/components/CommentForm";
import Link from "next/link";

import { Post, Comment, User } from "@/app/post/types";

async function getPost(post_id: string) {
  const res = await fetch(`http://localhost:8000/posts/${post_id}`, {
    cache: "no-store",
  });
  if (!res.ok) return null;
  return res.json();
}

export default function PostDetailPage() {
  const params = useParams();
  const id = params.id as string;
  const router = useRouter();

  const [post, setPost] = useState<Post | null>(null);
  const [user, setUser] = useState<User | null>(null);
  const [commentContent, setCommentContent] = useState("");
  const [comments, setComments] = useState<Comment[]>([]);

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      try {
        setUser(JSON.parse(storedUser));
      } catch {
        console.error("Lỗi khi đọc user");
      }
    }

    getPost(id).then((data) => {
      if (data) setPost(data);
    });

    fetch(`http://localhost:8000/posts/${id}/getAllComments`)
      .then((res) => res.json())
      .then((data) => {
        if (Array.isArray(data)) setComments(data);
        else setComments([]);
      })
      .catch(() => setComments([]));
  }, [id]);

  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
    router.push("/login");
  };

  const handleCommentSubmit = async () => {
    if (!commentContent.trim() || !user) return;

    try {
      const res = await fetch(`http://localhost:8000/posts/${id}/comments`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ content: commentContent, user_id: user.user_id }),
      });

      if (res.ok) {
        const newComment = await res.json();
        setComments((prev) => [newComment, ...prev]);
        setCommentContent("");
      } else {
        alert("Gửi bình luận thất bại.");
      }
    } catch (err) {
      console.error("Lỗi gửi bình luận", err);
    }
  };

  const handleReplySubmit = async (parentId: number, content: string) => {
    if (!content.trim() || !user) return;

    try {
      const res = await fetch(`http://localhost:8000/posts/${id}/comments`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          content,
          user_id: user.user_id,
          parent_id: parentId,
        }),
      });

      if (res.ok) {
        const newReply = await res.json();
        setComments((prev) => insertReply(prev, newReply));
      } else {
        alert("Gửi trả lời thất bại.");
      }
    } catch (err) {
      console.error("Lỗi gửi trả lời", err);
    }
  };

  const insertReply = (comments: Comment[], reply: Comment): Comment[] => {
    return comments.map((comment) => {
      if (comment.comment_id === reply.parent_id) {
        return {
          ...comment,
          replies: [reply, ...(comment.replies || [])],
        };
      } else if (comment.replies) {
        return {
          ...comment,
          replies: insertReply(comment.replies, reply),
        };
      } else {
        return comment;
      }
    });
  };

  if (!post) return <p className="text-center mt-10">Đang tải bài viết...</p>;

  return (
    <main className="min-h-screen bg-gray-50 pb-40 pt-17">
      <Header user={user} handleLogout={handleLogout} />

      <section className="max-w-7xl mx-auto p-6">
        <h2 className="text-3xl font-bold mb-4">{post.title}</h2>
        <p className="text-sm text-gray-500 mb-2">
          Tác giả: <b>{post.author_name}</b> | ID bài viết: {id}
        </p>

        <AuthorInfo categories={post.categories} tags={post.tags} />
        <PostContent content={post.content} />

        <div className="bg-white p-6 rounded-xl shadow">
          <h3 className="text-xl font-semibold mb-4">
            Bình luận ({comments.length})
          </h3>

          {!user && (
            <p className="text-sm text-gray-600 mb-6">
              <Link href="/login" className="text-blue-600 underline">
                Đăng nhập
              </Link>{" "}
              để bình luận.
            </p>
          )}

          <CommentList
            comments={comments}
            currentUserName={user?.user_name}
            onReplySubmit={handleReplySubmit}
          />
        </div>
      </section>

      {user && (
        <CommentForm
          commentContent={commentContent}
          setCommentContent={setCommentContent}
          onSubmit={handleCommentSubmit}
        />
      )}
    </main>
  );
}
