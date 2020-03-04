var path = require('path')

module.exports =  {
    context: path.resolve(__dirname, 'src/main/resources/jsx'),
    entry: {
        user_home: './user_home.jsx',
        calendar: './calendar.jsx'
    },
    devtool: 'sourcemaps',
    cache: false,
    output: {
        path: __dirname,
        filename: './src/main/webapp/WEB-INF/static/js/react/[name].bundle.js'
    },
    mode: 'none',
    module: {
        rules: [ {
            test: /\.jsx?$/,
            exclude: /(node_modules)/,
            use: {
                loader: 'babel-loader',
                options: {
                    presets: ['@babel/preset-env', '@babel/preset-react']
                }
            }
        }, {
            test: /\.css$/,
            use: ['style-loader', 'css-loader']
        }]
    }
};