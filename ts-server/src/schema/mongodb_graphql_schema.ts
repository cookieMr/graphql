import { 
    GraphQLID, 
    GraphQLInt, 
    GraphQLList,
    GraphQLNonNull,
    GraphQLObjectType, 
    GraphQLSchema, 
    GraphQLString 
} from "graphql";
import { User } from "../model/user";
import { Post } from "../model/post";
import { Hobby } from "../model/hobby";

const UserType: GraphQLObjectType = new GraphQLObjectType({
    name: "User",
    description: 'A user documentation...',
    fields: () => ({
        id: { type: new GraphQLNonNull(GraphQLID) },
        name: { type: new GraphQLNonNull(GraphQLString) },
        age: { type: new GraphQLNonNull(GraphQLInt) },
        profession: { type: GraphQLString },
        posts: {
            type: new GraphQLList(PostType),
            resolve(parent, _) {
                return Post.find({ userId: parent.id });
            }
        },
        hobbies: {
            type: new GraphQLList(HobbyType),
            resolve(parent, _) {
                return Hobby.find({ userId: parent.id });
            }
        }
    })
});

const HobbyType: GraphQLObjectType = new GraphQLObjectType({
    name: "Hobby",
    description: 'A hobby documentation...',
    fields: () => ({
        id: { type: new GraphQLNonNull(GraphQLID) },
        title: { type: new GraphQLNonNull(GraphQLString) },
        description: { type: GraphQLString },
        user: { 
            type: UserType,
            resolve(parent, _) {
                return User.findById(parent.userId);
            }
        }
    })
});

const PostType: GraphQLObjectType = new GraphQLObjectType({
    name: "Post",
    description: 'A Post documentation...',
    fields: () => ({
        id: { type: new GraphQLNonNull(GraphQLID) },
        comment: { type: new GraphQLNonNull(GraphQLString) },
        user: {
            type: UserType,
            resolve(parent, _) {
                return User.findById(parent.userId);
            }
        } 
    })
});

const RootQuery = new GraphQLObjectType({
    name: "RootQueryType",
    description: "A doc description of a root query...",
    fields: {
        user: {
            type: UserType,
            args: { id: { type: new GraphQLNonNull(GraphQLID) } },
            resolve(_, args) {
                return User.findById(args.id);
            }
        },
        users: {
            type: new GraphQLList(UserType),
            resolve(_1, _2) {
                return User.find({ });
            }
        },
        hobby: {
            type: HobbyType,
            args: { id: { type: new GraphQLNonNull(GraphQLID) } },
            resolve(_, args) {
                return Hobby.findById(args.id);
            }
        },
        hobbies: {
            type: new GraphQLList(HobbyType),
            resolve(_1, _2) {
                return Hobby.find({ });
            }
        },
        post: {
            type: PostType,
            args: { id: { type: new GraphQLNonNull(GraphQLID) } },
            resolve(_, args) {
                return Post.findById(args.id);
            }
        },
        posts: {
            type: new GraphQLList(PostType),
            resolve(_1, _2) {
                return Post.find({ });
            }
        }
    }
});

const Mutation = new GraphQLObjectType({
    name: "Mutation",
    fields: {
        createUser: {
            type: UserType,
            args: {
                name: { type: new GraphQLNonNull(GraphQLString) },
                age: { type: new GraphQLNonNull(GraphQLInt) },
                profession: { type: GraphQLString }
            },
            resolve(_, args) {
                let user = new User({
                    name: args.name,
                    age: args.age,
                    profession: args.profession
                });

                return user.save();
            }
        },
        updateUser: {
            type: UserType,
            args: {
                id: { type: new GraphQLNonNull(GraphQLID) },
                name: { type: GraphQLString },
                age: { type: GraphQLInt },
                profession: { type: GraphQLString }
            },
            resolve(_, args) {
                return User.findByIdAndUpdate(
                    args.id,
                    {
                        $set: {
                            name: args.name,
                            age: args.age,
                            profession: args.profession
                        }
                    },
                    { 
                        new: true, 
                        omitUndefined: true 
                    });
            }
        },
        removeUser: {
            type: UserType,
            args: {
                id: { type: new GraphQLNonNull(GraphQLID) },
            },
            resolve(_, args) {
                const removedUser = User.findByIdAndRemove(args.id).exec();

                if (removedUser) {
                    return removedUser;
                } else {
                    throw new Error("Cannot find and remove user with id: " + args.id);
                }
            }
        },
        createPost: {
            type: PostType,
            args: {
                comment: { type: new GraphQLNonNull(GraphQLString) },
                userId: { type: new GraphQLNonNull(GraphQLID) }
            },
            resolve(_, args) {
                let post = new Post({
                    comment: args.comment,
                    userId: args.userId
                });

                return post.save();
            }
        },
        updatePost: {
            type: PostType,
            args: {
                id: { type: new GraphQLNonNull(GraphQLID) },
                comment: { type: GraphQLString },
                userId: { type: GraphQLID }
            },
            resolve(_, args) {
                return Post.findByIdAndUpdate(
                    args.id,
                    {
                        $set: {
                            comment: args.comment,
                            userId: args.userId
                        }
                    },
                    { 
                        new: true, 
                        omitUndefined: true 
                    });
            }
        },
        removePost: {
            type: PostType,
            args: {
                id: { type: new GraphQLNonNull(GraphQLID) },
            },
            resolve(_, args) {
                const removedPost = Post.findByIdAndRemove(args.id).exec();

                if (removedPost) {
                    return removedPost;
                } else {
                    throw new Error("Cannot find and remove post with id: " + args.id);
                }
            }
        },
        createHobby: {
            type: HobbyType,
            args: {
                title: { type: new GraphQLNonNull(GraphQLString) },
                description: { type: GraphQLString },
                userId: { type: new GraphQLNonNull(GraphQLID) }
            },
            resolve(_, args) {
                let hobby = new Hobby({
                    title: args.title,
                    description: args.description,
                    userId: args.userId
                });

                return hobby.save();
            }
        },
        updateHobby: {
            type: HobbyType,
            args: {
                id: { type: new GraphQLNonNull(GraphQLID) },
                title: { type: GraphQLString },
                description: { type: GraphQLString },
                userId: { type: GraphQLID }
            },
            resolve(_, args) {
                return Hobby.findByIdAndUpdate(
                    args.id,
                    {
                        $set: {
                            title: args.title,
                            description: args.description,
                            userId: args.userId
                        }
                    },
                    { 
                        new: true, 
                        omitUndefined: true 
                    });
            }
        },
        removeHobby: {
            type: HobbyType,
            args: {
                id: { type: new GraphQLNonNull(GraphQLID) },
            },
            resolve(_, args) {
                const removedHobby = Hobby.findByIdAndRemove(args.id).exec();

                if (removedHobby) {
                    return removedHobby;
                } else {
                    throw new Error("Cannot find and remove hobby with id: " + args.id);
                }
            }
        }
    }
});

export const mongoQLSchema = new GraphQLSchema({
    query: RootQuery,
    mutation: Mutation
});
