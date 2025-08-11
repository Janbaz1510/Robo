# Shopsy

A small Flutter prototype for a product listing and cart flow using clean architecture.

## Features
- Product list from local JSON (no API)
- Product details
- Add to cart
- Cart with total price and item removal
- In-memory cart with optional persistence via SharedPreferences

## Architecture
- `domain/`: entities, abstract repositories, use cases
- `data/`: data sources, mappers, repository implementations
- `presentation/`: screens, widgets, controllers (ChangeNotifier)
- `core/di/`: provider-based service locator

## Run
Ensure Flutter SDK is installed.

```
flutter pub get
flutter run
```

## Assets
- Products JSON: `assets/products.json` (declared in `pubspec.yaml`)