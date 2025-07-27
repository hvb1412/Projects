import { Button } from "@/components/ui/button";

export default function CommentForm({
  commentContent,
  setCommentContent,
  onSubmit,
}: {
  commentContent: string;
  setCommentContent: (v: string) => void;
  onSubmit: () => void;
}) {
  return (
    <div className="fixed bottom-0 left-0 right-0 bg-white border-t border-gray-200 shadow z-50 p-4">
      <div className="max-w-7xl mx-auto flex items-end gap-2">
        <textarea
          className="flex-1 border border-gray-300 rounded-md p-3 resize-none focus:outline-blue-400 overflow-hidden"
          placeholder="Viết bình luận của bạn..."
          value={commentContent}
          onChange={(e) => {
            setCommentContent(e.target.value);
            const textarea = e.target;
            textarea.style.height = "auto";
            textarea.style.height = textarea.scrollHeight + "px";
          }}
        />
        <Button onClick={onSubmit}>Gửi bình luận</Button>
      </div>
    </div>
  );
}
