import mongoose, { Schema } from "mongoose";

const hobbySchema = new Schema({
    title: String,
    description: String,
    userId: String
});

export const Hobby = mongoose.model("Hobby", hobbySchema);
