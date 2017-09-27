import {Author} from './author.model';
import {Image} from './image.model';

export interface Screenshot {
  application_id: number;
  id: number;
  title: string;
  user: Author;
  images: Image;
  created_at: string;
  views_count: number;
  likes_count: number;
  comments_count: number;
  buckets_count: number;
  addedAt: string;
}
