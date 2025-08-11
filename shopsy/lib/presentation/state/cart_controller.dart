import 'package:flutter/foundation.dart';

import '../../domain/entities/cart_item.dart';
import '../../domain/entities/product.dart';
import '../../domain/repositories/cart_repository.dart';

class CartController extends ChangeNotifier {
  final CartRepository repository;

  CartController({required this.repository});

  Future<void> load() async {
    await repository.load();
    notifyListeners();
  }

  Map<String, CartItem> get itemsById => repository.itemsById;

  List<CartItem> get items => repository.itemsById.values.toList(growable: false);

  int get totalItems => repository.totalItems;

  double get totalPrice => repository.totalPrice;

  void add(Product product) {
    repository.add(product);
    repository.save();
    notifyListeners();
  }

  void removeOne(String productId) {
    repository.removeOne(productId);
    repository.save();
    notifyListeners();
  }

  void removeAll(String productId) {
    repository.removeAll(productId);
    repository.save();
    notifyListeners();
  }

  void clear() {
    repository.clear();
    repository.save();
    notifyListeners();
  }
}