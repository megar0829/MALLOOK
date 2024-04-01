import {create} from "zustand";

export interface Token {
	accessToken: string | null;
	refreshToken: string | null;
}

export interface UserToken {
	userToken: Token;
}

export interface UserTokenActions {
	setUserToken: (tokenData: Token) => void
	deleteUserToken: () => void
}

const userTokenData:Token = {
	accessToken: "",
	refreshToken: "",
}

const LoginState = create<UserToken & UserTokenActions>((set) => ({
	userToken: userTokenData,
	setUserToken: (userToken: Token) => {set( { userToken } ) },
	deleteUserToken: () => {set ( { userToken: userTokenData } )}
}))

export default LoginState