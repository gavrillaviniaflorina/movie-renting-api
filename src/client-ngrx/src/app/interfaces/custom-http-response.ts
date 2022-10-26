import { Movie } from "../movie/movie.model";

export interface CustomHttpResponse {
    timeStamp: Date;
    statusCode: number;
    status: string;
    message: string;
    reason: string;
    developerMessage: string;
    movies: Movie[]
  }
  