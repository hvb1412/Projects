import CommentItem from "./CommentItem";

interface Comment {
  comment_id: number;
  user_name: string;
  content: string;
  created_at: string;
  parent_id: number | null;
  replies?: Comment[];
}

export default function CommentList({
  comments,
  currentUserName,
  onReplySubmit,
}: {
  comments: Comment[];
  currentUserName?: string;
  onReplySubmit: (parentId: number, content: string) => void;
}) {
  if (comments.length === 0) {
    return <p className="text-sm text-gray-500">Chưa có bình luận nào.</p>;
  }

  return (
    <ul className="space-y-4">
      {comments.map((cmt) => (
        <li key={cmt.comment_id}>
          <CommentItem
            comment={cmt} 
            currentUserName={currentUserName}
            onReplySubmit={onReplySubmit}
          />
        </li>
      ))}
    </ul>
  );
}
