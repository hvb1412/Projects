"use client";

import "./signuppage.scss";
import Link from "next/link";
import { SignUpForm } from "@/app/(auth)/register/signup-form";
import MinimalHeader from "@/app/(auth)/components/MinimalHeader";

const SignupPage = () => {
  return (
    <div className="signup-background">
      {/* Header nằm trên cùng */}
      <MinimalHeader />

      {/* Form được đẩy xuống tránh bị header che */}
      <div className="signup-container mt-20">
        <h1 className="signup-title">Sign Up</h1>

        <SignUpForm />

        <div className="or-divider">Or</div>

        <div className="signup-link">
          Already have an account?
          <Link href="/login" className="login-link-text">
            Log in
          </Link>
        </div>
      </div>
    </div>
  );
};

export default SignupPage;
