const webpack = require('webpack')
const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const { CleanWebpackPlugin } = require('clean-webpack-plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const InterpolateHtmlPlugin = require('react-dev-utils/InterpolateHtmlPlugin')

const STATIC_RESOURCE_PATH = path.resolve(__dirname, '../main/resources/static')

module.exports = (env, options) => {
  const mode = options.mode

  const config = {
    context: path.resolve(__dirname, '.'),

    entry: './src/index.tsx',

    output: {
      path: STATIC_RESOURCE_PATH,
      filename: mode === 'production' ? 'bundle.[chunkhash].js' : 'bundle.js',
    },

    devServer: {
      contentBase: path.join(__dirname, 'dist'),
      compress: true,
      port: 9000,
      hot: true,
    },

    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src'),
      },
      extensions: ['.js', '.jsx', '.ts', '.tsx', '.json'],
    },

    module: {
      rules: [
        {
          test: /\.html$/,
          use: 'html-loader',
        },
        {
          test: /\.(png|svg|jpg|jpeg|gif)$/,
          use: 'url-loader',
        },
        {
          test: /\.(css|scss|sass)$/,
          use: [
            mode === 'production'
              ? 'style-loader'
              : MiniCssExtractPlugin.loader,
            'css-loader',
            'sass-loader',
          ],
        },
        {
          test: /\.(ts|tsx)$/,
          exclude: /(node_modules)/,
          use: ['babel-loader'],
        },
        {
          test: /\.(ts|tsx)$/,
          exclude: /(node_modules)/,
          use: ['eslint-loader'],
        },
      ],
    },

    plugins: [
      new webpack.SourceMapDevToolPlugin({}),
      new HtmlWebpackPlugin({
        template: path.resolve(__dirname, 'public/index.html'),
        favicon: 'public/favicon.ico',
      }),
      new CleanWebpackPlugin(),
      new MiniCssExtractPlugin({
        filename: '[name].css',
        chunkFilename: '[id].css',
      }),
      new InterpolateHtmlPlugin(HtmlWebpackPlugin, {
        PUBLIC_URL: 'public',
      }),
      new webpack.DefinePlugin({
        'process.env.PUBLIC_URL': JSON.stringify('public'),
      }),
    ],
  }

  return config
}