package com.example.superheroesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroesapp.model.Hero
import com.example.superheroesapp.model.HeroesRepository
import com.example.superheroesapp.ui.theme.SuperheroesTheme

@Composable
fun HeroCard(hero: Hero, modifier: Modifier = Modifier) {
  Card(
    modifier = modifier,
    elevation = CardDefaults.cardElevation(2.dp),
    shape = MaterialTheme.shapes.medium
  ) {
    Row(
      modifier = Modifier.padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column() {
        Text(
          stringResource(hero.nameRes),
          style = MaterialTheme.typography.displaySmall
        )
        Text(
          stringResource(hero.descriptionRes),
          style = MaterialTheme.typography.bodyLarge
        )
      }
      Spacer(Modifier.width(16.dp))
      Image(
        painter = painterResource(hero.imageRes),
        contentDescription = null,
        Modifier
          .size(72.dp)
          .clip(RoundedCornerShape(8.dp))
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun HeroCardPreview() {
  SuperheroesTheme() {
    HeroCard(HeroesRepository.heroes[0])
  }
}