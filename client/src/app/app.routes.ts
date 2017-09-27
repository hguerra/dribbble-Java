import {Routes} from '@angular/router';
import {ScreenshotsComponent} from './screenshots/screenshots.component';
import {FavoritesComponent} from './screenshots/favorites/favorites.component';
import {FavoritesDateComponent} from './screenshots/favorites-date/favorites-date.component';
import {FavoritesRecentComponent} from './screenshots/favorites-recent/favorites-recent.component';

export const ROUTES: Routes = [
  {path: '', component: ScreenshotsComponent},
  {path: 'screenshots', redirectTo: '', pathMatch: 'full'},
  {path: 'favorites', component: FavoritesComponent},
  {path: 'recent', component: FavoritesRecentComponent},
  {path: 'date', component: FavoritesDateComponent}
];
