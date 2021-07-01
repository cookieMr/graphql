import mongoose, { Schema } from "mongoose";

const postSchema = new Schema({
    comment: String,
    userId: String
});

export const Post = mongoose.model("Post", postSchema);
