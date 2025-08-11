import 'dart:convert';
import 'package:flutter/services.dart' show rootBundle;

class LocalProductDataSource {
  static const String productsAssetPath = 'assets/products.json';

  Future<List<Map<String, dynamic>>> loadProductsRaw() async {
    final jsonStr = await rootBundle.loadString(productsAssetPath);
    final List<dynamic> data = json.decode(jsonStr) as List<dynamic>;
    return data.cast<Map<String, dynamic>>();
  }
}