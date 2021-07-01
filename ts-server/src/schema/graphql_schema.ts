import { find, filter } from "lodash";
import { 
    GraphQLID, 
    GraphQLInt, 
    GraphQLList,
    GraphQLObjectType, 
    GraphQLSchema, 
    GraphQLString 
} from "graphql";

const userDatas = [
    { id: '1', name: 'Bond', age: 36, profession: 'Programmer' },
    { id: '13', name: 'Anna', age: 26, profession: 'Baker' },
    { id: '211', name: 'Bella', age: 16, profession: 'Mechanic' },
    { id: '19', name: 'Gina', age: 26, profession: 'Painter' },
    { id: '150', name: 'Goergina', age: 36, profession: 'Teacher' }
];

const hobbyDatas = [
    { id: '1', title: 'Programming', description: 'Using computers', userId: '1' },
    { id: '2', title: 'Rowing', description: 'Sweat before eating donouts', userId: '13' },
    { id: '3', title: 'Swimming', description: 'Getting wet', userId: '19' },
    { id: '4', title: 'Fencing', description: 'Another hobby', userId: '150' },
    { id: '5', title: 'Hiking', description: 'Exploring the world', userId: '1' }
];

const postDatas = [
    { id: '1', comment: 'Commenting on using computers', userId: '1' },
    { id: '2', comment: 'A comment on sweat before eating donouts', userId: '19' },
    { id: '3', comment: 'Getting wet while commenting', userId: '13' },
    { id: '4', comment: 'Another hobby with a comment', userId: '1' },
    { id: '5', comment: 'Exploring the world by posting a comment', userId: '1' }
];

const UserType: GraphQLObjectType = new GraphQLObjectType({
    name: "User",
    description: 'A user documentation...',
    fields: () => ({
        id: { type: GraphQLID },
        name: { type: GraphQLString },
        age: { type: GraphQLInt },
        profession: { type: GraphQLString },
        posts: {
            type: new GraphQLList(PostType),
            resolve(parent: { id: string }, _1: any) {
                return filter(postDatas, { userId: parent.id });
            }
        },
        hobbies: {
            type: new GraphQLList(HobbyType),
            resolve(parent: { id: string }, _1) {
                return filter(hobbyDatas, { userId: parent.id });
            }
        }
    })
});

const HobbyType: GraphQLObjectType = new GraphQLObjectType({
    name: "Hobby",
    description: 'A hobby documentation...',
    fields: () => ({
        id: { type: GraphQLID },
        title: { type: GraphQLString },
        description: { type: GraphQLString },
        user: { 
            type: UserType,
            resolve(parent: { userId: string }, _1) {
                return find(userDatas, { id: parent.userId });
            }
        }
    })
});

const PostType: GraphQLObjectType = new GraphQLObjectType({
    name: "Post",
    description: 'A Post documentation...',
    fields: () => ({
        id: { type: GraphQLID },
        comment: { type: GraphQLString },
        user: {
            type: UserType,
            resolve(parent: { userId: string }, _1) {
                return find(userDatas, { id: parent.userId });
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
            args: { id: { type: GraphQLID } },
            resolve(_1, args) {
                return find(userDatas, { id: args.id });
            }
        },
        users: {
            type: new GraphQLList(UserType),
            resolve(_1, _2) {
                return userDatas;
            }
        },
        hobby: {
            type: HobbyType,
            args: { id: { type: GraphQLID } },
            resolve(_1, args) {
                return find(hobbyDatas, { id: args.id });
            }
        },
        hobbies: {
            type: new GraphQLList(HobbyType),
            resolve(_1, _2) {
                return hobbyDatas;
            }
        },
        post: {
            type: PostType,
            args: { id: { type: GraphQLID } },
            resolve(_1, args) {
                return find(postDatas, { id: args.id });
            }
        },
        posts: {
            type: new GraphQLList(PostType),
            resolve(_1, _2) {
                return postDatas;
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
                name: { type: GraphQLString },
                age: { type: GraphQLInt },
                profession: { type: GraphQLString }
            },
            resolve(_1, args) {
                return {
                    name: args.name,
                    age: args.age,
                    profession: args.profession
                };
            }
        },
        createPost: {
            type: PostType,
            args: {
                comment: { type: GraphQLString },
                userId: { type: GraphQLID }
            },
            resolve(_1, args) {
                return {
                    comment: args.comment,
                    userId: args.userId
                };
            }
        },
        createHobby: {
            type: HobbyType,
            args: {
                title: { type: GraphQLString },
                description: { type: GraphQLString },
                userId: { type: GraphQLID }
            },
            resolve(_1, args) {
                return {
                    title: args.title,
                    description: args.description,
                    userId: args.userId
                };
            }
        }
    }
});

export const graphQLSchema = new GraphQLSchema({
    query: RootQuery,
    mutation: Mutation
});
